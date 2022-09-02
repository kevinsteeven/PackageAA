/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 *<pre>
Accepts two dates as input and calculates the difference between them in days.
 
Validation:
Both start date and end dates must be Not NULL.
Providing the output variable is mandatory. 

 * </pre>
 * 
 
 */

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be displayable on GUI.
@CommandPkg(
		//Unique name inside a package and label to display.
		name = "DateDiff", label = "[[DateDiff.label]]", 
		node_label = "[[DateDiff.node_label]]", description = "[[DateDiff.description]]", icon = "datediff.svg", 
		
		//Return type information. return_type ensures only the right kind of variable is provided on the UI. 
		return_label = "[[DateDiff.return_label]]", return_type = DataType.NUMBER, return_required = true)
public class DateDifference {
	@Execute
	public Value<?> action (
			//Idx 1 would be displayed first, with a text box for entering the value.
			@Idx(index = "1", type = AttributeType.DATETIME) 
			//UI labels.
			@Pkg(label = "Start Date") 
			//Ensure that a validation error is thrown when the value is null.
			@NotEmpty ZonedDateTime startDate,
			@Idx(index = "2", type = AttributeType.DATETIME) 
			//UI labels.
			@Pkg(label = "End Date") 
			//Ensure that a validation error is thrown when the value is null.
			@NotEmpty ZonedDateTime endDate)
			{
		
			long diffInDays  = ChronoUnit.DAYS.between(startDate, endDate);
			// return the value as a number variable
			return new NumberValue(diffInDays);
			}
}