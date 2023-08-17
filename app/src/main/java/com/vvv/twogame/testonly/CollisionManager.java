//package com.vvv.twogame.game1;
//
//import android.graphics.RectF;
//import java.util.List;
//
//public class CollisionManager {
//
//    public static void checkCollisions(PlayerSpaceship player, List<Enemy> enemies, List<Projectile> projectiles) {
//        checkPlayerEnemyCollisions(player, enemies);
//        checkProjectileEnemyCollisions(projectiles, enemies);
//    }
//
//    private static void checkPlayerEnemyCollisions(PlayerSpaceship player, List<Enemy> enemies) {
//        RectF playerHitbox = player.getHitbox();
//        for (Enemy enemy : enemies) {
//            RectF enemyHitbox = enemy.getHitbox();
//            if (RectF.intersects(playerHitbox, enemyHitbox)) {
//                // Handle player-enemy collision
//                // For example, end the game or decrease player's health
//            }
//        }
//    }
//
//    private static void checkProjectileEnemyCollisions(List<Projectile> projectiles, List<Enemy> enemies) {
//        for (Projectile projectile : projectiles) {
//            RectF projectileHitbox = projectile.getHitbox();
//            for (Enemy enemy : enemies) {
//                RectF enemyHitbox = enemy.getHitbox();
//                if (RectF.intersects(projectileHitbox, enemyHitbox)) {
//                    // Handle projectile-enemy collision
//                    // For example, remove the projectile and enemy
//                }
//            }
//        }
//    }
//}
