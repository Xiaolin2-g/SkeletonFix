package com.xiaolin.skeletonfix;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkeletonFix implements ModInitializer {

	public static final String MOD_ID = "skeletonfix";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Skeleton Aiming Fix...");
	}
}
