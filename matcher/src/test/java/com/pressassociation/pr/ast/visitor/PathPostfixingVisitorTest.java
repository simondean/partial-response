package com.pressassociation.pr.ast.visitor;

import com.google.common.collect.ImmutableList;

import com.pressassociation.pr.ast.AstNode;
import com.pressassociation.pr.ast.Field;
import com.pressassociation.pr.ast.Word;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

/**
 * Tests for {@link PathPostfixingVisitor}.
 *
 * @author Matt Nathan
 */
@RunWith(Parameterized.class)
public class PathPostfixingVisitorTest extends TransformingVisitorTestBase<PathPostfixingVisitor, AstNode> {

  @Parameterized.Parameters
  public static Collection<Object[]> parameters() {
    return ImmutableList.<Object[]>builder()
                        .add(args(new Word("after"), "*", "*/after"))
                        .add(args(new Word("after"), "items", "items/after"))
                        .add(args(new Word("after"), "items,other", "items/after,other/after"))
                        .add(args(new Word("after"), "items/id", "items/id/after"))
                        .add(args(new Word("after"), "items/id,other/*", "items/id/after,other/*/after"))
                        .add(args(new Word("after"), "items(id)", "items(id)/after"))
                        .build();
  }

  private static Object[] args(Field postfix, String source, String expected) {
    return genArgs(postfix, source, expected);
  }

  private final Field postfix;

  public PathPostfixingVisitorTest(Field postfix, String source, String expected) {
    super(source, expected);
    this.postfix = postfix;
  }

  @Override
  protected PathPostfixingVisitor createVisitor() {
    return new PathPostfixingVisitor(postfix);
  }
}