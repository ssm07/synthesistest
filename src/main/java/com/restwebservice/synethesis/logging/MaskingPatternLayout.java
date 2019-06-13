package com.restwebservice.synethesis.logging;


import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * Logback appender to mask a given pattern with a mask value.
 * Both pattern and mask value can be configured in the logback.xml
 * E.g. Sensitive data like emails will be masked to * in the logs.
 * Logback layout:
 * <layout class="services.utils.MaskingPatternLayout">
 * <mask>*</mask>
 * <patternsProperty>^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$</patternsProperty>
 * <pattern>%d [%thread] %-5level %logger{35} - %msg%n</pattern>
 * </layout>
 */
@Component
public class MaskingPatternLayout extends PatternLayout{
    private String patternsProperty;
    private Optional<Pattern> pattern;

    public String getPatternsProperty() {
        return patternsProperty;
    }

    public void setPatternsProperty(String patternsProperty) {
        this.patternsProperty = patternsProperty;
        if (this.patternsProperty != null) {
            this.pattern = Optional.of(Pattern.compile(patternsProperty, Pattern.MULTILINE));
        } else {
            this.pattern = Optional.empty();
        }
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        final StringBuilder message = new StringBuilder(super.doLayout(event));

        if (pattern.isPresent()) {
            Matcher matcher = pattern.get().matcher(message);
            while (matcher.find()) {

                int group = 1;
                while (group <= matcher.groupCount()) {
                    if (matcher.group(group) != null) {
                        for (int i = matcher.start(group); i < matcher.end(group); i++) {
                            message.setCharAt(i, '*');
                        }
                    }
                    group++;
                }
            }
        }
        return message.toString();
    }

}

