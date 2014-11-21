package com.pressassociation.pr.ast;

import com.pressassociation.pr.ast.visitor.AstVisitor;

/**
 * Represents a wildcard name.
 *
 * @author Matt Nathan
 */
public class Wildcard extends Name {
  @SuppressWarnings("UtilityClassWithoutPrivateConstructor")
  private static final class Holder {
    private static final Wildcard INSTANCE = new Wildcard();
  }

  public static Wildcard getSharedInstance() {
    return Holder.INSTANCE;
  }

  private Wildcard() {
  }

  @Override
  public void apply(AstVisitor visitor) {
    visitor.visitWildcard(this);
  }
}