using UnityEngine;

public class Bonus : MonoBehaviour {

    //Quando l'oggetto 'bonus' collide con un altro oggetto, se questo è il Player, allora manda il comando al 'Player'
    private void OnTriggerEnter2D(Collider2D collision) 
    {
        if (collision.tag == "Player") 
        {
            if ((gameObject.name == "Power Up(Clone)") && (PlayerShooting.instance.weaponPower < PlayerShooting.instance.maxweaponPower))
                PlayerShooting.instance.weaponPower++;

            if (gameObject.name == "Life Up(Clone)" && Player.instance.CurrentLife < 3)
                Player.instance.PlusCurrentLife();
            Destroy(gameObject);
        }
    }
}