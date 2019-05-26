// IControl.aidl
package com.rq.practice.control;

import com.rq.practice.control.Message;

interface IControl {

    void control(in Message msg);

    String receive();
}
