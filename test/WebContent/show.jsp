<%@page import="entity.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="Echart/echarts.min.js"></script>
</head>
<body>
<%         request.setCharacterEncoding("utf-8");
List <Info> infos =(List<Info>) request.getAttribute("infos"); 

int i=0;%>
<div align="center">
<div id="main"  align="center"style="width: 600px;height:400px;" ></div>
    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));
 
        var option = {
        		dataset: {
        	        source: [
        	            [  '省份','确诊人数'],
        	            <%
        				//获取request域中的数据
        				if(infos!=null){
        				for(Info info:infos){i++;
        			%>
        					[<%=Integer.parseInt(info.getConfirmed_num())%>,'<%=info.getProvince()%>'],
        			<%
        				
        				}
        			
        				}
        			%>
        	        ]
        	    },
        	    grid: {containLabel: true},
        	    xAxis: {},
        	    yAxis: {type: 'category'},
        	    series: [
        	        {
        	            type: 'bar',
        	            encode: {
        	                // 将 "amount" 列映射到 X 轴。
        	                x: '省份',
        	                // 将 "product" 列映射到 Y 轴。
        	                y: '确诊人数'
        	            }
        	        }]
        };
 
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    </div>
    <table border="1px" align="center">
         <tr>
         <th>省份</th>
         <th>确诊人数</th>
         <th>死亡人数</th>
         <th>恢复人数</th>
         </tr>
         <%

         if(infos!=null){
           for(Info info:infos){
        	   %>
        	   
        	   <tr>        	       
        	       <td><%=info.getProvince() %></td>
        	       <td><%=info.getConfirmed_num() %></td>
        	       <td><%=info.getDead_num() %></td>
        	       <td><%=info.getCured_num() %></td>
        	   </tr>
        	   <%
           }
         }
         %>
      </table>
</body>
</html>