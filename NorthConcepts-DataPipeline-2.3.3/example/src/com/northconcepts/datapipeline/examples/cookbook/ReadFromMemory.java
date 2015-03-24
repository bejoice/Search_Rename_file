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
import com.northconcepts.datapipeline.core.DataWriter;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.core.RecordList;
import com.northconcepts.datapipeline.csv.CSVWriter;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.memory.MemoryReader;

public class ReadFromMemory {

    public static void main(String[] args) {
        Record record1 = new Record();
        record1.getField("name", true).setValue("John Wayne");
        record1.getField("balance", true).setValue(156.35);

        Record record2 = new Record();
        record2.getField("name", true).setValue("Peter Parker");
        record2.getField("balance", true).setValue(0.96);
        
        RecordList recordList = new RecordList();
        recordList.add(record1);
        recordList.add(record2);
        
        DataReader reader = new MemoryReader(recordList);
        DataWriter writer = new CSVWriter(new File("example/data/output/credit-balance-02.csv"));
    
        JobTemplate.DEFAULT.transfer(reader, writer);
    }
    
}
