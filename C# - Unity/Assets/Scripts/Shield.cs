using UnityEngine;
using UnityEngine.UI;

public class Shield : MonoBehaviour
{
    public Text on;

    public static Shield instance;

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (gameObject.GetComponent<SpriteRenderer>().material.color != new Color(1f, 1f, 1f, 132))
        { 
            if (collision.tag == "Shield")
            {
                gameObject.GetComponent<SpriteRenderer>().material.color = new Color(1f, 1f, 1f, 132);
                on.On();
            } 
        }else
        {
            if (collision.tag == "Enemy")
            {
                collision.GetComponent<Enemy>().GetDamage(1);
                gameObject.GetComponent<SpriteRenderer>().material.color = new Color(1f, 1f, 1f, 1);
                on.Off();
            }else if (collision.tag == "EnemyProjectile")
            {
                collision.GetComponent<Projectile>().Destruction();
                gameObject.GetComponent<SpriteRenderer>().material.color = new Color(1f, 1f, 1f, 1);
                on.Off();
            }
        }
    }
}