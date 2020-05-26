package cl.abcdin.sst.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.directwebremoting.ConversionException;
import org.directwebremoting.extend.AbstractConverter;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.NonNestedOutboundVariable;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;
import org.directwebremoting.extend.ProtocolConstants;

public class CustomDateConverter extends AbstractConverter {
	private SimpleDateFormat sdfddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdfddMMyyyyHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pattern pDate = Pattern.compile("^(([\\d]){2}/){2}([\\d]){4}$");
	Pattern pDateWHour = Pattern.compile("^(([\\d]){2}/){2}([\\d]){4}\\s([\\d]){2}:([\\d]){2}$");
	
	public OutboundVariable convertOutbound(Object data, OutboundContext outctx) throws ConversionException {
		Calendar cal;
		int yyyy = 0, MM = 0, dd = 0, HH = 0, mm = 0, ss = 0;
		
		if ((data instanceof Calendar)) {
			cal = (Calendar) data;
		} else {
			if ((data instanceof java.util.Date)) {
				java.util.Date date = (java.util.Date) data;
				cal = Calendar.getInstance();
				cal.setTime(date);
			} else {
				throw new ConversionException(data.getClass()); 
			}
		}

		try {
			yyyy = cal.get(Calendar.YEAR);
			MM = cal.get(Calendar.MONTH);
			dd = cal.get(Calendar.DATE);
			HH = cal.get(Calendar.HOUR_OF_DAY);
			mm = cal.get(Calendar.MINUTE);
			ss = cal.get(Calendar.SECOND);

		} catch (Exception e) {
			throw new ConversionException(data.getClass());
		}	  
		return new NonNestedOutboundVariable("new Date(" + yyyy + ", " + MM + ", " + dd + ", " + HH + ", " + mm + ", " + ss + ")");
	}

	@Override
	public Object convertInbound(Class<?> paramType, InboundVariable data) throws ConversionException {
        if (data.isNull() || data.getValue().trim().equals("null") || data.getValue().trim().equals("")) {
            return null;
        }

        String value = data.getValue();
        // If the text is null then the whole bean is null
        if (value.trim().equals(ProtocolConstants.INBOUND_NULL)) {
            return null;
        }

        try {
	    	
	    	if (value.contains("%2F") | value.contains("%20") | value.contains("%3A")) {
	    		value = value.replaceAll("%2F", "/");
	    		value = value.replaceAll("%20", " ");
	    		value = value.replaceAll("%3A", ":");
	    		Matcher mDate = pDate.matcher(value);
	    		Matcher mDateWHour = pDateWHour.matcher(value);
	    		if(mDate.find()) {
		    		return sdfddMMyyyy.parse(value);
	    		}
	    		if(mDateWHour.find()) {
		    		return sdfddMMyyyyHHmm.parse(value);
	    		}
	    		return null;
	    	}
	    	
            long millis = 0;
            if (value.length() > 0) {
                millis = Long.parseLong(value);
            }

            Date date = new Date(millis);
            if (paramType == Date.class)
            {
                return date;
            }
            else if (paramType == java.sql.Date.class)
            {
                return new java.sql.Date(date.getTime());
            }
            else if (paramType == Time.class)
            {
                return new Time(date.getTime());
            }
            else if (paramType == Timestamp.class)
            {
                return new Timestamp(date.getTime());
            }
            else if (paramType == Calendar.class)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return cal;
            }
            else
            {
                throw new ConversionException(paramType);
            }
        }
        catch (ConversionException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new ConversionException(paramType, ex);
        }
    }	
	
}