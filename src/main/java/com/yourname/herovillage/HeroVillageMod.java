package com.yourname.herovillage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class HeroVillageMod implements ModInitializer {

	@Override
	public void onInitialize() {
		// Apply Hero of the Village effect every server tick
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				// Check if player doesn't have the effect, add it
				if (player.getStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE) == null) {
					// Duration in ticks (20 ticks = 1 second)
					// 2147483647 = max int value (essentially infinite)
					// Amplifier 0 = no visual changes (level 1)
					player.addStatusEffect(new StatusEffectInstance(
						StatusEffects.HERO_OF_THE_VILLAGE,
						2147483647,  // Duration (very long)
						0,            // Amplifier (0 = level 1)
						false,        // Ambient (won't show particles)
						false,        // Show particles (false = invisible)
						false         // Show icon (false = no icon)
					));
				}
			}
		});
	}
}