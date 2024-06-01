<#if message??>
  <div id="message" class="${message.type}">${message.text}</div>
<#else>
  <div id="message" class="INFO" style="display:none">
    <!-- keep here for client-side messages -->
  </div>
</#if>

<#if message2??>
    <div id="message" class="${message2.type}">${message2.text}</div>
<#else>
    <div id="message" class="INFO" style="display:none">
        <!-- keep here for client-side messages -->
    </div>
</#if>
