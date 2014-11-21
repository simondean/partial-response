/*
 * Copyright (c) 2014 Press Association Limited
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.pressassociation.pr.parser;

import com.google.common.testing.NullPointerTester;

import org.junit.Test;

/**
 * Tests for {@link Parser} for null checking.
 *
 * @author Matt Nathan
 */
public class ParserNullTest {
  @Test
  public void testNulls() throws Exception {
    NullPointerTester tester = new NullPointerTester();
    tester.testAllPublicConstructors(Parser.class);
    tester.testAllPublicStaticMethods(Parser.class);
    tester.testAllPublicInstanceMethods(new Parser());
  }
}
