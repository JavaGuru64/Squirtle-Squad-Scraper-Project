package edu.mansfield.squirtle_squad.controller;

import edu.mansfield.squirtle_squad.delegates.ScanDelegate;

public abstract class ScanController {
	
	protected ScanDelegate delegate;
	
	public ScanController(){
		initializeScan();
	}
	
	public ScanController(ScanDelegate delegate){
		this.delegate = delegate;
		initializeScan();
	}
	
	
	protected abstract boolean initializeScan();
	public abstract void scan();
}
