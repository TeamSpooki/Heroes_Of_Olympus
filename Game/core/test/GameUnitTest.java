import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enemy;
import com.mygdx.game.GameUnit;
import com.mygdx.game.Hero;
import com.mygdx.game.Location;
import com.mygdx.level.Level1;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestContext.class)
public class GameUnitTest {

	@Test
	public void getPieceHero() {
		Level1 level = new Level1();

		GameUnit hero = level.getHeroes().get(0);
		GameUnit heroAtLocation = level.findNearestHeroTouch(hero.getX(), hero.getY());

		assertEquals(hero, heroAtLocation);
	}

	@Test
	public void getPieceEnemy() {
		Level1 level = new Level1();

		GameUnit enemy = level.getHeroes().get(0);
		GameUnit enemyAtLocation = level.findNearestHeroTouch(enemy.getX(), enemy.getY());

		assertEquals(enemy, enemyAtLocation);
	}

	@Test
	public void findNearestHero() {
		Level1 level = new Level1();

		Hero helen = new Hero(TextureRegion.split(new Texture(Gdx.files.internal("Heroes/HelenHealthBar.png")), 64, 64),
				"HELEN", 3, 4, 10);
		Hero achille = new Hero(
				TextureRegion.split(new Texture(Gdx.files.internal("Heroes/AchillesHealthBar.png")), 64, 64), "ACHILLE",
				3, 4, 10);

		GameUnit nearestHero1 = level.findNearestHero(helen.getX(), helen.getY());
		GameUnit nearestHero2 = level.findNearestHero(achille.getX(), achille.getY());
		System.out.println(nearestHero1 + ", " + nearestHero2);

		assertEquals(nearestHero1, nearestHero2);
	}

	@Test
	public void findNearestEnemy() {
		Level1 level = new Level1();

		Enemy titan = new Enemy(
				TextureRegion.split(new Texture(Gdx.files.internal("Level1/CyclopsHealthBar.png")), 64, 64), "CYCLOPS",
				1, 3, 20);
		Enemy enemy1 = new Enemy(
				TextureRegion.split(new Texture(Gdx.files.internal("Level1/SkeletonBowHealthBar.png")), 64, 64),
				"SKELETON BOW", 1, 4, 5);

		GameUnit nearestEnemy1 = level.findNearestEnemyTouch(titan.getX(), titan.getY());
		GameUnit nearestEnemy2 = level.findNearestEnemyTouch(enemy1.getX(), enemy1.getY());

		assertEquals(nearestEnemy1, nearestEnemy2);
	}

	@Test
	public void findNearestHeroTouch() {
		Level1 level = new Level1();

		GameUnit hero = level.getHeroes().get(0);

		GameUnit closestHero = level.findNearestHeroTouch(hero.getX(), hero.getY());

		assertEquals(hero, closestHero);
	}
	
	@Test
	public void findNearestEnemyTouch() {
		Level1 level = new Level1();

		GameUnit enemy = level.getEnemies().get(0);

		GameUnit closestEnemy = level.findNearestEnemyTouch(enemy.getX(), enemy.getY());

		assertEquals(enemy, closestEnemy);
	}

	@Test
	public void moveHero() {
		
		Level1 level = new Level1();
		
		Location from = new Location(level.getHeroes().get(0).getX(),level.getHeroes().get(0).getY());
		Location to = new Location(level.getHeroes().get(0).getX(),level.getHeroes().get(0).getY() + 10);
		
		level.movePiece(from, to);
		
		assertEquals(level.getHeroes().get(0).getLocation(), to);
		
	}

	@Test
	public void checkHeroesDead() {
		Level1 level = new Level1();

		for (GameUnit hero : level.getHeroes()) {
			hero.toggleDead();
		}

		assertEquals(level.heroesDead(), true);
	}
	
	@Test
	public void checkEnemiesDead() {
		Level1 level = new Level1();

		for (GameUnit enemy : level.getEnemies()) {
			enemy.toggleDead();
		}

		assertEquals(level.enemiesDead(), true);
	}

}
