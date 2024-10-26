using UnityEngine;
using UnityEngine.SceneManagement;

//Con questo script definisco lo sprite e la salute del player.
public class Player : MonoBehaviour
{
    public GameObject destructionFX;

    public int MaxLife = 3;
    public int CurrentLife;

    public static Player instance;

    public Life life;

    private void Start()
    {
        CurrentLife = MaxLife;
        life.SetLife(CurrentLife);
    }

    private void Awake()
    {
        if (instance == null) 
            instance = this;
    }

    public void PlusCurrentLife()
    {
        CurrentLife += 1;
        life.SetLife(CurrentLife);
    }

    //Metodo per danneggiare i nemici
    public void GetDamage(int damage)   
    {
        CurrentLife -= damage;
        life.SetLife(CurrentLife);
        if (CurrentLife == 0)
        {
            Destruction();
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
        }
    }
        
    //Metodo per la distruzione del player
    void Destruction()
    {
        Instantiate(destructionFX, transform.position, Quaternion.identity); //Genero l'effetto grafico e distruggo l'oggetto 'Player'
        Destroy(gameObject);
    }
}