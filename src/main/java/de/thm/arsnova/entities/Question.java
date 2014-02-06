/*
 * Copyright (C) 2012 THM webMedia
 *
 * This file is part of ARSnova.
 *
 * ARSnova is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ARSnova is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.thm.arsnova.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.ezmorph.bean.MorphDynaBean;

public class Question {

	private String type;
	private String questionType;
	private String questionVariant;
	private String subject;
	private String text;
	private boolean active;
	private String releasedFor;
	private List<PossibleAnswer> possibleAnswers;
	private boolean noCorrect;
	// TODO: We currently need both sessionId and sessionKeyword, but sessionKeyword will not be persisted.
	private String sessionId;
	private String sessionKeyword;
	private long timestamp;
	private int number;
	private int duration;
	private int piRound;
	private boolean showStatistic; // sic
	private boolean showAnswer;
	private boolean abstention;
	private String _id;
	private String _rev;
	private Object _attachments;

	// grid square
	private String gridsize;
	private String image;

	/**
	 * Returns the gridsize
	 * @return the gridsize
	 */
	public String getGridsize() {
		return gridsize;
	}

	/**
	 * Sets the gridsize
	 * @param gridsize the gridsize to set
	 */
	public void setGridsize(String gridsize) {
		this.gridsize = gridsize;
	}

	/***
	 * Returns the image.
	 * @return the image as base64.
	 */
	public final String getImage() {
		return image;
	}

	/***
	 * Sets the image.
	 * @param image image as base64.
	 */
	public final void setImage(String image) {
		this.image = image;
	}


	public final String getType() {
		return type;
	}

	public final void setType(final String type) {
		this.type = type;
	}

	public final String getQuestionType() {
		return questionType;
	}

	public final void setQuestionType(final String questionType) {
		this.questionType = questionType;
	}

	public final String getQuestionVariant() {
		return questionVariant;
	}

	public final void setQuestionVariant(final String questionVariant) {
		this.questionVariant = questionVariant;
	}

	public final String getSubject() {
		return subject;
	}

	public final void setSubject(final String subject) {
		this.subject = subject;
	}

	public final String getText() {
		return text;
	}

	public final void setText(final String text) {
		this.text = text;
	}

	public final boolean isActive() {
		return active;
	}

	public final void setActive(final boolean active) {
		this.active = active;
	}

	public final String getReleasedFor() {
		return releasedFor;
	}

	public final void setReleasedFor(final String releasedFor) {
		this.releasedFor = releasedFor;
	}

	public final List<PossibleAnswer> getPossibleAnswers() {
		return possibleAnswers;
	}

	public final void setPossibleAnswers(final List<PossibleAnswer> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public final boolean isNoCorrect() {
		return noCorrect;
	}

	public final void setNoCorrect(final boolean noCorrect) {
		this.noCorrect = noCorrect;
	}

	public final String getSessionId() {
		return sessionId;
	}

	public final void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public final String getSession() {
		return sessionId;
	}

	public final void setSession(final String session) {
		this.sessionId = session;
	}

	public final String getSessionKeyword() {
		return this.sessionKeyword;
	}

	public final void setSessionKeyword(final String keyword) {
		this.sessionKeyword = keyword;
	}

	public final long getTimestamp() {
		return timestamp;
	}

	public final void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public final int getNumber() {
		return number;
	}

	public final void setNumber(final int number) {
		this.number = number;
	}

	public final int getDuration() {
		return duration;
	}

	public final void setDuration(final int duration) {
		this.duration = duration;
	}

	public int getPiRound() {
		return piRound;
	}

	public void setPiRound(int piRound) {
		this.piRound = piRound;
	}

	public boolean isShowStatistic() {
		return showStatistic;
	}

	public void setShowStatistic(boolean showStatistic) {
		this.showStatistic = showStatistic;
	}

	public boolean isShowAnswer() {
		return showAnswer;
	}

	public void setShowAnswer(boolean showAnswer) {
		this.showAnswer = showAnswer;
	}

	public boolean isAbstention() {
		return abstention;
	}

	public void setAbstention(boolean abstention) {
		this.abstention = abstention;
	}

	public final String get_id() {
		return _id;
	}

	public final void set_id(final String _id) {
		this._id = _id;
	}

	public final String get_rev() {
		return _rev;
	}

	public final void set_rev(final String _rev) {
		this._rev = _rev;
	}

	@Override
	public final String toString() {
		return "Question type '" + this.type + "': " + this.subject + ";\n" + this.text + this.possibleAnswers;
	}

	/**
	 * @return the _attachments
	 */
	public Object get_attachments() {
		/* return image instead of attachment -> workaround for the bad REST-API */
		return this.image;
	}

	/**
	 * @param _attachments the _attachments to set
	 */
	public void set_attachments(Object _attachments) {
		/* convert Object to MorphDynaBean Object */
		MorphDynaBean mdb = (MorphDynaBean) _attachments;
		
		/* get attachment and convert to MorphDynaBean Object */
		MorphDynaBean mdb2 = (MorphDynaBean) mdb.get("attachment");
		
		/* get image (base64) from attachment */
		DefaultHttpClient httpClient = new DefaultHttpClient();
		StringBuilder result = new StringBuilder();
		try {
				System.out.println("=================== ATTACHMENT =================");
				HttpGet getRequest = new HttpGet("http://127.0.0.1:5984/arsnova/" + this._id + "/attachment?rev=" + _rev);
				HttpResponse response = httpClient.execute(getRequest);
				BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

				String output;
				while ((output = br.readLine()) != null) {
					result.append(output);
				}

			} catch (IOException e) {
				System.out.println("=================== ATTACHMENT FAIL =================");
		}
		/* set the result to image */
		setImage(result.toString());
		
		/* return unchanged _attachment object -> workaround for the bad REST-API */
		this._attachments = _attachments;
	}

}
