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
import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.StreamWriter;
import com.northconcepts.datapipeline.csv.CSVReader;
import com.northconcepts.datapipeline.group.AggregateReader;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.transform.BasicFieldTransformer;
import com.northconcepts.datapipeline.transform.TransformingReader;

public class FindTheMinimumMaximumAverageSumCount {
    
    public static final Logger log = DataEndpoint.log; 

    public static void main(String[] args) throws Throwable {
        DataReader reader = new CSVReader(new File("example/data/input/credit-balance-01.csv"))
            .setFieldNamesInFirstRow(true);
        
        // CSV fields are strings, convert them to double
        reader = new TransformingReader(reader)
            .add(new BasicFieldTransformer("Balance").stringToDouble())
            .add(new BasicFieldTransformer("CreditLimit").stringToDouble());
        
        AggregateReader aggregateReader = new AggregateReader(reader);
        
        aggregateReader
            .minimum("Rating")
            .maximum("Rating")
            .count("Rating")
            .maximum("Balance")
            .maximum("Balance")
            .average("Balance")
            .sum("Balance")
            .average("CreditLimit")
            ;
        
        JobTemplate.DEFAULT.transfer(aggregateReader, new StreamWriter(System.out));
        
        log.info(aggregateReader.getSum("Balance"));
        log.info(aggregateReader.getAverage("Balance"));
        log.info(aggregateReader);
    }
    
}
