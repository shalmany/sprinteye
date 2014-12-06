package com.slb.core.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class  CustomObjectMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
        super();
         registerModule(new Hibernate4Module());
        //configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        registerModule(new JodaModule());
        setDateFormat(new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss"));
        
        
        //setLocale(new Locale("pt", "BR"));
        
    }
}