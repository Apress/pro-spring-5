<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<div xmlns:jsp="http://java.sun.com/JSP/Page"
     xmlns:c="http://java.sun.com/jsp/jstl/core"
     xmlns:spring="http://www.springframework.org/tags"
     xmlns:form="http://www.springframework.org/tags/form"
     xmlns:joda="http://www.joda.org/joda/time/tags"
     version="2.0">
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
    <jsp:output omit-xml-declaration="yes"/>

    <spring:message code="label_contact_info" var="labelContactInfo"/>
    <spring:message code="label_contact_first_name" var="labelContactFirstName"/>
    <spring:message code="label_contact_last_name" var="labelContactLastName"/>
    <spring:message code="label_contact_birth_date" var="labelContactBirthDate"/>
    <spring:message code="label_contact_description" var="labelContactDescription"/>
    <spring:message code="label_contact_update" var="labelContactUpdate"/>
    <spring:message code="date_format_pattern" var="dateFormatPattern"/>
    <spring:message code="label_contact_photo" var="labelContactPhoto"/>

    <spring:url value="/contacts/photo" var="contactPhotoUrl"/>
    <spring:url value="/contacts" var="editContactUrl"/>

    <h1>${labelContactInfo}</h1>

    <div id="contactInfo">

        <c:if test="${not empty message}">
            <div id="message" class="${message.type}">${message.message}</div>
        </c:if>

        <table>
            <tr>
                <td>${labelContactFirstName}</td>
                <td>${contact.firstName}</td>
            </tr>
            <tr>
                <td>${labelContactLastName}</td>
                <td>${contact.lastName}</td>
            </tr>
            <tr>
                <td>${labelContactBirthDate}</td>
                <td><joda:format value="${contact.birthDate}" pattern="${dateFormatPattern}"/></td>
            </tr>
            <tr>
                <td>${labelContactDescription}</td>
                <td>${contact.description}</td>
            </tr>
            <tr>
                <td>${labelContactPhoto}</td>
                <td><img src="${contactPhotoUrl}/${contact.id}"></img></td>
            </tr>
        </table>

        <a href="${editContactUrl}/${contact.id}?form">${labelContactUpdate}</a>
    </div>
</div>
