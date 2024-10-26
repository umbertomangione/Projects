using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;


//Questo script definisce i Nemici e il loro comportamento. 

public class Enemy : MonoBehaviour {
    private AudioSource FireEffect;

    [Tooltip("Health points in integer")]
    public int health;

    [Tooltip("Enemy's projectile prefab")]
    public GameObject Projectile;

    [Tooltip("VFX prefab generating after destruction")]
    public GameObject destructionVFX;
    public GameObject hitEffect;
    
    [HideInInspector] public int shotChance; //Probabilità che i nemici sparino durante il path
    [HideInInspector] public float shotTimeMin, shotTimeMax; //Tempo massimo e minimo per sparare durante il path
    

    private void Start()
    {
        FireEffect = GetComponent<AudioSource>();
        Invoke("ActivateShooting", Random.Range(shotTimeMin, shotTimeMax));
    }

    private void Update()
    {
        Invoke("ActivateShooting", Random.Range(shotTimeMin, shotTimeMax));
    }

    //coroutine per il colpo
    void ActivateShooting() 
    {
        if (Random.value < (float)shotChance / 100)
        {
            Instantiate(Projectile, gameObject.transform.position, Quaternion.identity);
            FireEffect.Play();
        }
    }

    //Metodo per danneggiare il 'Nemico'
    public void GetDamage(int damage) 
    {
        health -= damage;
        if (health <= 0)
        {
            if (gameObject.name == "Enemy_Boss(Clone)")
            {
                Score.ScoreValue += 1000;
                SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
            }
            else
                Score.ScoreValue += 150;
            Destruction();
        }
        else
            Instantiate(hitEffect, transform.position, Quaternion.identity, transform);
    }    

    //Se il 'Nemico' collide con il 'Player', il 'Player' riceverà lo stesso danno dei proiettili
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.tag == "Player")
        {
            if (Projectile.GetComponent<Projectile>() != null)
                Player.instance.GetDamage(Projectile.GetComponent<Projectile>().Enemydamage);
            else
                Player.instance.GetDamage(1);
        }
    }

    //Metodo per distruggere l'oggetto 'Nemico'
    void Destruction()                           
    {        
        Instantiate(destructionVFX, transform.position, Quaternion.identity); 
        Destroy(gameObject);
    }
}
