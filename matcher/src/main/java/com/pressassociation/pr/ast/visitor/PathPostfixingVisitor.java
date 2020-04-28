/*
 * The MIT License (MIT)
 *
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
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.pressassociation.pr.ast.visitor;

import com.google.common.base.Preconditions;

import com.pressassociation.pr.ast.*;
import com.pressassociation.pr.parser.Parser;

/**
 * Postfix some node to the visited nodes.
 *
 * For example:<br>
 * {@code items/id,items/name} with a postfix value of {@code type} will become
 * {@code items/id/type,items/name/type}<br>
 * {@code items(id, name)} with a postfix of {@code type} will become {@code items(id, name)/type}
 *
 * @author Matt Nathan
 */
public class PathPostfixingVisitor extends TransformingVisitor<AstNode> {

  private final Field postfix;
  private AstNode result;

  public PathPostfixingVisitor(Field postfix) {
    this.postfix = postfix;
  }

  @Override
  public void visitFields(Fields fields) {
    Field field = (Field) appendPostfix(fields.getField());
    AstNode next = new PathPostfixingVisitor(postfix).applyTo(fields.getNext());
    result = new Fields(field, next);
  }

  @Override
  public void visitPath(Path path) {
    result = appendPostfix(path);
  }

  @Override
  public void visitSubSelection(SubSelection subSelection) {
    result = appendPostfix(subSelection);
  }

  @Override
  public void visitWildcard(Wildcard wildcard) {
    result = appendPostfix(wildcard);
  }

  @Override
  public void visitWord(Word word) {
    result = appendPostfix(word);
  }

  @Override
  public AstNode getResult() {
    Preconditions.checkState(result != null, "Cannot get the result without first using the visitor");
    return result;
  }

  private Path appendPostfix(Node wildcard) {
    return new Path(wildcard, postfix);
  }

  private AstNode appendPostfix(AstNode node) {
    // todo: replace parser usage with ast creation
    return new Parser().parse(node + "/" + postfix);
  }
}
