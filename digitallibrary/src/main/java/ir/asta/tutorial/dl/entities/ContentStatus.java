package ir.asta.tutorial.dl.entities;

import ir.asta.wise.core.util.locale.LocaleUtil;

public enum ContentStatus {
	VERIFIED, REJECTED, IN_PROCESS;
	private static String PROP_BUNDLE_KEY = "contentStatus_";
	public String toString() {
		return LocaleUtil.getText(PROP_BUNDLE_KEY + this.name());
	}
}
