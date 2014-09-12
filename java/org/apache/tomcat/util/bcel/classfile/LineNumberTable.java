/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.tomcat.util.bcel.classfile;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * This class represents a table of line numbers for debugging
 * purposes. This attribute is used by the <em>Code</em> attribute. It
 * contains pairs of PCs and line numbers.
 *
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     Code
 * @see LineNumber
 */
public final class LineNumberTable extends Attribute {

    private static final long serialVersionUID = 6585122636118666124L;
    private int line_number_table_length;
    private LineNumber[] line_number_table; // Table of line/numbers pairs


    /*
     * @param name_index Index of name
     * @param length Content length in bytes
     * @param line_number_table Table of line/numbers pairs
     * @param constant_pool Array of constants
     */
    public LineNumberTable(int name_index, int length, LineNumber[] line_number_table,
            ConstantPool constant_pool) {
        super(name_index, length, constant_pool);
        setLineNumberTable(line_number_table);
    }


    /**
     * Construct object from file stream.
     * @param name_index Index of name
     * @param length Content length in bytes
     * @param file Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    LineNumberTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, (LineNumber[]) null, constant_pool);
        line_number_table_length = (file.readUnsignedShort());
        line_number_table = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            line_number_table[i] = new LineNumber(file);
        }
    }


    /**
     * @param line_number_table the line number entries for this table
     */
    public final void setLineNumberTable( LineNumber[] line_number_table ) {
        this.line_number_table = line_number_table;
        line_number_table_length = (line_number_table == null) ? 0 : line_number_table.length;
    }
}
