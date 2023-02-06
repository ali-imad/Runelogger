package model.game.world.actor;

public enum AttackOutcome {
    DAMAGE,  // dmg < this.hp
    KILL,  // dmg >= this.hp
    BLOCK,  // reduction
    MISS,  // 0
}
