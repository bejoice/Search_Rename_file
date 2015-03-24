/*
 * Copyright (c) 2006-2010 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.datapipeline.examples.cookbook;

import java.io.File;

import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.StreamWriter;
import com.northconcepts.datapipeline.csv.CSVReader;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.transform.IncludeFields;
import com.northconcepts.datapipeline.transform.TransformingReader;

public class SelectAndArrangeFields {
    
    public static void main(String[] args) throws Throwable {
        DataReader reader = new CSVReader(new File("example/data/input/credit-balance-01.csv"))
            .setFieldNamesInFirstRow(true);
        
        TransformingReader transformingReader = new TransformingReader(reader);
        
        // remove all fields except 'FirstName', 'LastName', and 'Rating'; 
        // arrange them in that order
        transformingReader.add(
                new IncludeFields()
                    .add("FirstName")
                    .add("LastName")
                    .add("Rating")
        );
        
        JobTemplate.DEFAULT.transfer(transformingReader, new StreamWriter(System.out));
    }
    
}
