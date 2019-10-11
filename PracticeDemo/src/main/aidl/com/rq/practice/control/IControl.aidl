// IControl.aidl
package com.rq.practice.control;

import com.rq.practice.control.Message;
import com.rq.practice.control.IReceiveListener;

interface IControl {

    void control(in Message msg);

    void setReceiveListener(IReceiveListener listener);
}
