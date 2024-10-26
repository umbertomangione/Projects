using System.Collections;
using System.Collections.Generic;
using UnityEngine;


// Con questo script si definisce la grandezza dei 'Bordi' della Viewport, quando un oggetto si trova oltre i bordi questo viene distrutto.

public class Boundary : MonoBehaviour {

    BoxCollider2D BoundsCollider;

    //Riceve le componenti del collider e cambia i bordi
    private void Start()
    {
        BoundsCollider = GetComponent<BoxCollider2D>();
        ResizeCollider();
    }

    //Con questa funziona si riesce a cambiare la grandezza della Viweport utilizzando multipli di 1.5
    void ResizeCollider() 
    {        
        Vector2 viewportSize = Camera.main.ViewportToWorldPoint(new Vector2(1, 1)) * 2;
        BoundsCollider.size = viewportSize;
    }

    //Quando un oggetto collide lo distrugge
    private void OnTriggerExit2D(Collider2D collision) 
    {
        if (collision.tag == "Projectile")
        {
            Destroy(collision.gameObject);
        }
        else if (collision.tag == "Bonus")
        {
            Destroy(collision.gameObject);
        }
    }

}
