package github.com.shuheiyoshida.ros_android_trial.simple_pub;

/**
 * @brief simple_pub, it publishes string which is set at a view of Android
 */

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.ros.android.AppCompatRosActivity;
import org.ros.android.MessageCallable;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

//import github.com.shuheiyoshida.ros_android_trial.simple_pub.SimplePublisher;


public class MainActivity extends AppCompatRosActivity {

    private RosTextView<std_msgs.String> rosTextView;
    //private RosTextView<ros_android_basic_apps.AndroidTest> testView; // Check of usage of custom message
    private SimplePublisher simplePublisher;

    public MainActivity() {
        super("simple_pub", "simple_pub");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare rosTextView
        rosTextView = (RosTextView<std_msgs.String>) findViewById(R.id.text);
        rosTextView.setTopicName("chatter");
        rosTextView.setMessageType(std_msgs.String._TYPE);
        rosTextView.setMessageToStringCallable(new MessageCallable<String, std_msgs.String>() {
            @Override
            public String call(std_msgs.String message) {
                return message.getData();
            }
        });
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        simplePublisher = new SimplePublisher();
        // Text data of simplePublisher changes by clicking a button.

        // At this point, the use has already been prompted to either enter the URI
        // of a master to use, or to start a master locally.

        // Execute nodes
        // SimplePublisher node
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(simplePublisher, nodeConfiguration);

        // The RosTextView is also a NodeMain that must be executed in order to
        // start displaying incoming messages.
        nodeMainExecutor.execute(rosTextView, nodeConfiguration);
    }
}
