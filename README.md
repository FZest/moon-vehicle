# moon-vehicle
## 功能
模拟运行多辆月球车，每辆月球车（Vehicle）每秒都将自己的位置信息发送给地面控制中心（Controller）。  
月球车的位置信息包括：  
* 当前坐标（currentLocation）：月球车在这一秒的当前坐标。坐标定义如下：以屏幕左上角为坐标原点，横轴水平向右，纵轴垂直向下，横坐标（x）与纵坐标（y）均为非负数。
* 目的坐标（destLocation）：月球车此次移动的目的坐标。月球车在整个移动过程中，会有若干个目的坐标。
* 移动方向（direction）：月球车沿着水平线顺时针旋转direction角度的方向移动。
* 移动速度（speed）：月球车每秒移动的距离。
* 转向角度（steeringAngle）：当月球车遇到障碍物或者到达目的位置准备往下一个目的位置移动的时候，月球车需要在当前移动方向上顺时针旋转steeringAngle角度。

月球车启动后，每秒会将当前位置信息发送给地面控制中心，而控制中心每隔500毫秒打印一次月球车的报告位置与预测位置。
## 预测位置计算
由于月球车与地面控制中心存在2秒的通信延迟，所以需要在报告位置的基础上预测2秒后的位置。  
已知当前坐标curLocation，预计移动方向direction（=当前移动方向+转向角度），预计移动距离moveDistance（=移动速度*通信延迟）  
计算预测坐标predictLocation的公式如下：  
`predictLocation.x = curLocation.x + moveDistance * Math.cos(direction)`  
`predictLocation.y = curLocation.y + moveDistance * Math.sin(direction)`  
如果在通信延迟内月球车移动会超过目的位置，那么月球车应当在目的位置停下来，此时的预测位置应当是目的位置。
## 月球车路线输入文件
每辆月球车的移动路线会预先设置好并保存在文件vehicle.lines中。  
文件中每一行代表一辆月球车的移动线路，描述了在移动过程中每秒的位置信息，每行格式如下：  
`月球车ID 位置1 位置2 位置3...`  
每秒的位置存储格式如下：  
`currentLocation.x,currentLocation.y,destLocation.x,destLocation.y,direction,speed,steeringAngle`

由于每辆月球车需要模拟至少15分钟的移动路线，所以需要借助程序随机生成。  
月球车路线随机生成程序请参考com.remark.media.exam.common.RouterLineBuilder

具体输入文件请参考src/main/resources/vehicle.lines
## 月球车与控制中心通信
因为会有很多辆月球车同时与控制中心通信，所以采用akka框架实现通信，每辆月球车和控制中心都是一个actor。
## 使用与运行
将项目从github clone到本地后直接导入到IDE（Eclipse或者IDEA）中即可。  
启动控制中心：运行com.remark.media.exam.main.ControllerStarter的main方法。  
启动月球车：运行com.remark.media.exam.main.VehicleStarter的main方法。
