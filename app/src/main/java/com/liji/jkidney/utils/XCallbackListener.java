package com.liji.jkidney.utils;


public abstract class XCallbackListener {

	protected abstract void callback(Object... obj);

	public void call(Object... obj) {
		try {
			callback(obj);
		} catch (Exception e) {
			JLogUtils.E(e);
		}
	}

}
