<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/template/includes.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Specify Parameters</title>

  <link href="../params/reportParams.css" rel="stylesheet"/>

  <script>
    function execute() {
      var tripId = $("#tripId").val();
      var format = $('input:radio[name=format]:checked').val();
      
  	  var url = apiUrlPrefix + "/command/tripWithTravelTimes" 
  	          + "?tripId=" + tripId
  			  + "&format=" + format;

   	  // Actually do the API call
   	  location.href = url;
    }
  </script>
  
</head>
<body>

<%@include file="/template/header.jsp" %>

<div id="title">
   Select Parameters for Trip API
</div>
   
<div id="mainDiv">   
  <div class="param">
    <label for="trip">Trip:</label>
    <input type="text" id="tripId" size="35" />
  </div>
   
   <%-- Create json/xml format radio buttons --%>
   <jsp:include page="../params/jsonXmlFormat.jsp" />
   
   <%-- Create submit button --%> 
   <jsp:include page="../params/submitApiCall.jsp" />   
</div>

</body>
</html>