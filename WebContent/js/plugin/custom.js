
(function($) {
    $.selectInput = function(element, options) {
        var defaults = {
    		inputStyle: 'width:15%;font-size:9pt;margin-right:2px',
    		selectStyle:'width:44%'
        };

        var plugin = this;

        plugin.settings = {};

        var $element = $(element), // reference to the jQuery version of DOM element
             element = element;    // reference to the actual DOM element

        plugin.init = function() {
            plugin.settings = $.extend({}, defaults, options);
            var div = $('<div></div>');
            var input = $('<input/>')
            .attr('id','input-no-' + $element.attr('id'))
            .attr('name','input-no-' + $element.attr('name'))
            .attr('type','text')
            .attr('style',plugin.settings.inputStyle).change(function(event){
            	var new_v = $(this).val();
            	var sel = $('#' + this.name.replace('input-no-',''));
            	sel.val(new_v);
            	if (sel.val() === '') {
            		$(this).val('');
            	}
            	sel.change();
            });
            
            $element.change(function(event){
            	var new_v = $(this).val();
            	var inp = $('#' + 'input-no-' + this.name);
            	inp.val(new_v);
            });
            
            $element.attr('style',plugin.settings.selectStyle);
            $element.before(input);

        };
        plugin.init();

    };

    $.fn.selectInput = function(options) {
        return this.each(function() {
            if (undefined == $(this).data('selectInput')) {
                var plugin = new $.selectInput(this, options);
                $(this).data('selectInput', plugin);
            }
        });
    };

})(jQuery);