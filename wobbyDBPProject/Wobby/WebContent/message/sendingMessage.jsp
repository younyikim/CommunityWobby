<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
<meta charset="EUC-KR">
<title>Send Message</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

<div class="container">
   <div class="row">
      <div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">Message Form</h3>
        </div>
        <div class="panel-body">
            <form role="form" class="form-horizontal">
                <div class="form-group">
                  <label class="col-sm-2" for="inputTo">To</label>
                  <div class="col-sm-10"><input type="email" class="form-control" id="inputTo" placeholder="comma separated list of recipients"></div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">Subject</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div>
                </div>
                <div class="form-group">
                  <label class="col-sm-12" for="inputBody">Message</label>
                  <div class="col-sm-12"><textarea class="form-control" id="inputBody" rows="8" data-gramm="true" data-txt_gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" data-gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" spellcheck="false" data-gramm_editor="true" style="z-index: auto; position: relative; line-height: 26.6667px; font-size: 14px; transition: none; overflow: auto; background: transparent !important;"></textarea><div><div style="z-index: 2; opacity: 1; margin-left: 794px; margin-top: 197px;" class="gr-textarea-btn " data-grammarly-reactid=".3"><div title="Protected by Grammarly" class="gr-textarea-btn_status" data-grammarly-reactid=".3.0"></div></div></div></div>
                </div>
                
                <div style="text-align: center;">
          <button id="singlebutton" name="singlebutton" class="btn btn-primary" onclick="location.href='messageBox.jsp'">Send</button>
              </div>
            </form>
        </div>
        
      </div>
   </div>


</div>

</body></html>