/// <reference path="jquery.js"/>
/*
 * jmodal
 * version: 2.0 (05/13/2009)
 * @ jQuery v1.3.*
 *
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2008, 2009 Jericho [ thisnamemeansnothing[at]gmail.com ] 
 *  
*/
jQuery.extend(jQuery.fn, {
    hideJmodal: function() {
        jQuery('#jmodal-overlay').animate({ opacity: 0 }, function() { jQuery(this).css('display', 'none') });
        jQuery('#jquery-jmodal').animate({ opacity: 0 }, function() { jQuery(this).css('display', 'none') });
    },
    jmodal: function(setting) {
        var ps = jQuery.fn.extend({
            data: {},
            marginTop: 100,
            buttonText: { ok: 'Ok', cancel: 'Cancel' },
            okEvent: function(e) { },
            initWidth: 400,
            fixed: false,
            title: 'JModal Dialog',
            content: 'This is a jquery plugin!'
        }, setting);

        ps.docWidth = jQuery(document).width();
        ps.docHeight = jQuery(document).height();

        if (jQuery('#jquery-jmodal').length == 0) {
            jQuery('<div id="jmodal-overlay" class="jmodal-overlay"/>' +
                '<div class="jmodal-main" id="jquery-jmodal" >' +
                    '<table cellpadding="0" cellspacing="0">' +
                        '<tr>' +
                            '<td class="jmodal-top-left jmodal-png-fiexed">&nbsp;</td>' +
                            '<td class="jmodal-border-top jmodal-png-fiexed">&nbsp;</td>' +
                            '<td class="jmodal-top-right jmodal-png-fiexed">&nbsp;</td>' +
                        '</tr>' +
                    '<tr>' +
                        '<td class="jmodal-border-left jmodal-png-fiexed">&nbsp;</td>' +
                        '<td >' +
                            '<div class="jmodal-title" />' +
                            '<div class="jmodal-content" id="jmodal-container-content" />' +
                            '<div class="jmodal-bottom">' +
                                '<input type="button" value="' + ps.buttonText.ok + '" />&nbsp;&nbsp;<input type="button" value="' + ps.buttonText.cancel + '" />' +
                            '</div>' +
                        '</td>' +
                        '<td class="jmodal-border-right jmodal-png-fiexed">&nbsp;</td>' +
                    '</tr>' +
                    '<tr>' +
                        '<td class="jmodal-bottom-left jmodal-png-fiexed">&nbsp;</td>' +
                        '<td class="jmodal-border-bottom jmodal-png-fiexed">&nbsp;</td>' +
                        '<td class="jmodal-bottom-right jmodal-png-fiexed">&nbsp;</td>' +
                    '</tr>' +
                    '</table>' +
                '</div>').appendTo(jQuery(document.body));
            //jQuery(document.body).find('form:first-child') || jQuery(document.body)
        }
        else {
            jQuery('#jmodal-overlay').css({ opacity: 0, 'display': 'block' });
            jQuery('#jquery-jmodal').css({ opacity: 0, 'display': 'block' });
        }
        jQuery('#jmodal-overlay').css({
            height: ps.docHeight,
            opacity: 0
        }).animate({ opacity: 0.5 });

        jQuery('#jquery-jmodal').css({
            position: (ps.fixed ? 'fixed' : 'absolute'),
            width: ps.initWidth,
            left: (ps.docWidth - ps.initWidth) / 2,
            top: (ps.marginTop + document.documentElement.scrollTop)
        }).animate({ opacity: 1 });

        jQuery('#jquery-jmodal')
            .find('.jmodal-title')
                .html(ps.title)
                    .next()
                        .next()
                            .children('input:first-child')
                                .attr('value', ps.buttonText.ok)
                                    .unbind('click')
                                        .one('click', function(e) {
                                            var args = {
                                                complete: jQuery.fn.hideJmodal
                                            };

                                            ps.okEvent(ps.data, args);
                                        })
                                            .next()
                                                .attr('value', ps.buttonText.cancel)
                                                    .one('click', jQuery.fn.hideJmodal);
        if (typeof ps.content == 'string') {
            jQuery('#jmodal-container-content').html(ps.content);
        }
        if (typeof ps.content == 'function') {
            var e = jQuery('#jmodal-container-content');
            e.holder = jQuery('#jquery-jmodal');
            ps.content(e);
        }
    }
})