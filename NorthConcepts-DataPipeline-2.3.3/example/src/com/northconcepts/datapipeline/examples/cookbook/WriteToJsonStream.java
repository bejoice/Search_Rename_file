/*
 * Copyright (c) 2006-2013 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.datapipeline.examples.cookbook;

import java.io.FileWriter;

import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.core.RecordList;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.json.SimpleJsonWriter;
import com.northconcepts.datapipeline.memory.MemoryReader;

public class WriteToJsonStream {
	
	
/* Produces the following JSON (line breaks added for clarity)
  
[
   {
      "stageName":"John Wayne",
      "realName":"Marion Robert Morrison",
      "gender":"male",
      "city":"Winterset",
      "balance":156.35
   },
   {
      "stageName":"Spiderman",
      "realName":"Peter Parker",
      "gender":"male",
      "city":"New York",
      "balance":-0.96
   }
]

*/

    public static void main(String[] args) throws Throwable {

        Record record1 = new Record();
        record1.getField("stageName", true).setValue("John Wayne");
        record1.getField("realName", true).setValue("Marion Robert Morrison");
        record1.getField("gender", true).setValue("male");
        record1.getField("city", true).setValue("Winterset");
        record1.getField("balance", true).setValue(156.35);

        Record record2 = new Record();
        record2.getField("stageName", true).setValue("Spiderman");
        record2.getField("realName", true).setValue("Peter Parker");
        record2.getField("gender", true).setValue("male");
        record2.getField("city", true).setValue("New York");
        record2.getField("balance", true).setValue(-0.96);
               
        MemoryReader reader = new MemoryReader(new RecordList(record1, record2));
        
        SimpleJsonWriter writer = new SimpleJsonWriter(new FileWriter("example/data/output/credit-balance-04.json"));
        
        JobTemplate.DEFAULT.transfer(reader, writer);
    }

}
