/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.query.sqm.tree.expression;

import org.hibernate.query.sqm.NodeBuilder;
import org.hibernate.query.sqm.SemanticQueryWalker;
import org.hibernate.query.sqm.SqmExpressable;
import org.hibernate.query.sqm.tree.domain.SqmPath;

/**
 * Represents the {@code SIZE()} function.
 *
 * @author Steve Ebersole
 * @author Gunnar Morling
 */
public class SqmCollectionSize extends AbstractSqmExpression<Integer> {
	private final SqmPath<?> pluralPath;

	public SqmCollectionSize(SqmPath<?> pluralPath, NodeBuilder nodeBuilder) {
		this( pluralPath, nodeBuilder.getIntegerType(), nodeBuilder );
	}

	public SqmCollectionSize(SqmPath<?> pluralPath, SqmExpressable<Integer> sizeType, NodeBuilder nodeBuilder) {
		super( sizeType, nodeBuilder );
		this.pluralPath = pluralPath;
	}

	public SqmPath<?> getPluralPath() {
		return pluralPath;
	}

	@Override
	public <T> T accept(SemanticQueryWalker<T> walker) {
		return walker.visitPluralAttributeSizeFunction( this );
	}

	@Override
	public String asLoggableText() {
		return "SIZE(" + pluralPath.asLoggableText() + ")";
	}

	@Override
	public void appendHqlString(StringBuilder sb) {
		sb.append( "size(" );
		pluralPath.appendHqlString( sb );
		sb.append( ')' );
	}

}