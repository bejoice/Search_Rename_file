/*
 * Copyright (c) 2006-2010 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.datapipeline.examples.cookbook;

import java.io.File;

import org.apache.log4j.Logger;

import com.northconcepts.datapipeline.core.DataEndpoint;
import com.northconcepts.datapipeline.core.DataException;
import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.NullWriter;
import com.northconcepts.datapipeline.csv.CSVReader;
import com.northconcepts.datapipeline.job.JobTemplate;

public class HandleExceptions {
    
    public static final Logger log = DataEndpoint.log; 

    public static void main(String[] args) throws Throwable {
        try {
            DataReader reader = new CSVReader(new File("example/data/input/bad-credit-balance-01.csv"))
                .setFieldNamesInFirstRow(true);
            
            JobTemplate.DEFAULT.transfer(reader, new NullWriter());
        } catch (DataException e) {
            log.info(e.get("CSVReader.lineText"));
            log.info(e.get("CSVReader.column"));
            log.info(e.get("TextReader.line"));
            log.info(e.getRecord());
            throw e;
        }
    }

}
