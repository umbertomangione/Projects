using System.Collections;
using System.Collections.Generic;
using UnityEngine;


//Con questo Script definisco tutto quello che riguarda i Proiettili.


public class Projectile : MonoBehaviour {

    [Tooltip("Damage which the Player projectile deals to Enemys. Integer")]
    public int Playerdamage;

    [Tooltip("Damage which the Enemys projectile deals to the Player. Integer")]
    public int Enemydamage;

    [Tooltip("Whether the projectile belongs to the ‘Enemy’ or to the ‘Player’")]
    public bool enemyBullet;

    [Tooltip("Whether the projectile is destroyed in the collision, or not")]
    public bool destroyedByCollision;

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (enemyBullet && collision.tag == "Player") //In base al tipo di oggetto che va a colpire fa danno al tag corrispondente
        {
            Player.instance.GetDamage(Enemydamage); 
            if (destroyedByCollision)
                Destruction();
        }
        else if (!enemyBullet && collision.tag == "Enemy")
        {
            collision.GetComponent<Enemy>().GetDamage(Playerdamage);
            if (destroyedByCollision)
                Destruction();
        }
    }

    public void Destruction() 
    {
        Destroy(gameObject);
    }
}


