package github.com.shuheiyoshida.ros_android_trial.simple_pub;

/**
 * @brief simple_pub, it publishes string which is set at a view of Android
 */

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.ros.android.MessageCallable;
import org.ros.android.AppCompatRosActivity;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
//import org.ros.rosjava_tutorial.pubsub.Talker;
//import org.ros.rosjava_messages.std_msgs;
//import org.ros.concurrent.CancellableLoop;
//import org.ros.namespace.GraphName;
//import org.ros.node.AbstractNodeMain;
//import org.ros.node.ConnectedNode;
//import org.ros.node.NodeMain;
//import org.ros.node.topic.Publisher;

public class MainActivity extends AppCompatRosActivity {

    private RosTextView<std_msgs.String> rosTextView;

    public MainActivity() {
        super("simple_pub", "simple_pub");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {

        // Execute nodes
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());

    }
}
