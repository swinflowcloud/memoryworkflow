/**
 * Copyright 2008-2019 Dahai Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudibpm.core.antlr.evaluation;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.cloudibpm.core.WorkflowEntity;
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.data.NullValue;
import com.cloudibpm.core.data.expression.Expression;

public class EvalExprCalculatorUtil {
	/**
	 * Get CommonTokenStream from source code
	 */
	public static WorkflowEntity computeValue(Expression rule, WfProcess parent) throws Exception{
		try {
			if (rule == null)
				return new NullValue();
			if (rule.isConstant()) {
				return rule.evaluteConstant();
			} else if (rule.isNullRule()) {
				return new NullValue();
			}
			// 词法级别的字符流 (before v4.7)
			// ANTLRInputStream in = new ANTLRInputStream(rule.toString());
			// EvaluationExpressionLexer l = new EvaluationExpressionLexer(in);
			CharStream stream = CharStreams.fromString(rule.toString());
			EvaluationExpressionLexer l = new EvaluationExpressionLexer(stream);
			// 语法级别的字符串流
			CommonTokenStream tokens = new CommonTokenStream(l);
			EvaluationExpressionParser parser = new EvaluationExpressionParser(tokens);
			rule.setExceptionString(null);
			ParseTree tree = parser.stmt(); // parse
			EvaluationExpressionVisitor<WorkflowEntity> eval = new EvalExprUtil(rule, parent);
			WorkflowEntity result = eval.visit(tree);
			return result;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
