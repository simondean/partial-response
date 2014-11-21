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

package com.pressassociation.pr.ast;

import com.pressassociation.pr.ast.visitor.AstVisitor;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A single resolved word in a path.
 *
 * @author Matt Nathan
 */
public class Word extends Name {
  private final String stringValue;

  public Word(String stringValue) {
    checkNotNull(stringValue);
    checkArgument(!stringValue.isEmpty(), "stringValue cannot be empty");
    this.stringValue = stringValue;
  }

  public String getStringValue() {
    return stringValue;
  }

  @Override
  public void apply(AstVisitor visitor) {
    visitor.visitWord(this);
  }
}
