package com.redenexus.nospher.treasures.boss.data;

import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.util.Helper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter @Setter
public class Boss {

    private final String name;
    private final EntityType entityType;
    private final String displayName;
    private final double damage;
    private final double health;
    private final int speed;

    public void summon(Player player, Integer level) {
        Location location = player.getLocation();
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) livingEntity).getHandle();
        livingEntity.setCustomName(Helper.colorize(displayName.replace("{level}", String.valueOf(level))));
        livingEntity.setCustomNameVisible(true);
        livingEntity.setMaxHealth(health);
        livingEntity.setHealth(health);
        livingEntity.getEquipment().clear();
        if(livingEntity instanceof Zombie)
            ((Zombie) livingEntity).setBaby(false);
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, speed));
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 9999, 15));
        livingEntity.setMetadata("treasureBoss", new FixedMetadataValue(Treasures.getInstance(), true));
        nmsEntity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
    }

}
