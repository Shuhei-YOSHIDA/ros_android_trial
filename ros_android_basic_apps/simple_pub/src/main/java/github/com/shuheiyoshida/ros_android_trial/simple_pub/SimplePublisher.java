package github.com.shuheiyoshida.ros_android_trial.simple_pub;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

import java.sql.Time;

import geometry_msgs.Point;
import ros_android_basic_apps.AndroidTest;


/**
 * Publisher class for my trial, this is a node which will be executed.
 */
public class SimplePublisher extends AbstractNodeMain {
    private String topic_name;
    private String text_data;

    public SimplePublisher() {
        topic_name = "chatter";
        text_data = "text data is not set yet.";
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("ros_android_trial/simple_pub");
    }

    @Override
    public void onStart(final ConnectedNode connectedNode) {
        final Publisher<std_msgs.String> testPublisher =
                connectedNode.newPublisher(topic_name, std_msgs.String._TYPE);
        final Publisher<ros_android_basic_apps.AndroidTest> customMsgPublisher =
                connectedNode.newPublisher("custom_test_msg_topic", AndroidTest._TYPE);

        // This CancellableLoop will be canceled automatically when the node shuts down.
        connectedNode.executeCancellableLoop(new CancellableLoop() {
            //@Override
            //protected void setup() {}

            // Main loop
            @Override
            protected void loop() throws InterruptedException {
                std_msgs.String str = testPublisher.newMessage();
                str.setData(text_data);
                testPublisher.publish(str);

                ros_android_basic_apps.AndroidTest at = customMsgPublisher.newMessage();

                at.setStr(str);
                at.getPose().getPosition().setX(1.);
                at.getPose().getPosition().setY(2.);
                at.getPose().getPosition().setZ(3.);
                at.getPose().getOrientation().setW(1.);
                at.getPose().getOrientation().setX(0.);
                at.getPose().getOrientation().setY(0.);
                at.getPose().getOrientation().setZ(0.);

                at.getImage().getHeader().setFrameId("custom_camera");
                at.getImage().getHeader().setStamp(connectedNode.getCurrentTime());
//
//                geometry_msgs.Pose p = new geometry_msgs.Pose();
//                geometry_msgs.Point po = null;
//                po.setX(1.);
//                po.setY(2.);
//                po.setZ(3.);
//                p.setPosition(po);
//                geometry_msgs.Quaternion qu = null;
//                qu.setW(1.);
//                qu.setX(0.);
//                qu.setY(0.);
//                qu.setZ(0.);
//                p.setOrientation(qu);
//                at.setPose(p);
//
//                sensor_msgs.Image img = null;
//                std_msgs.Header header = null;
//                header.setFrameId("custom_camera");
//                org.ros.message.Time t = connectedNode.getCurrentTime();
//                header.setStamp(t);
//                img.setHeader(header);

                customMsgPublisher.publish(at);

                Thread.sleep(1000);
            }
        });
    }
}
