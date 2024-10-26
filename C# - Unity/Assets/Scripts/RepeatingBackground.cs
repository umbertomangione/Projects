using UnityEngine;

// Questo script è utilizzaato per creare l'effetto di movimento. 

public class RepeatingBackground : MonoBehaviour
{
    [Tooltip("vertical size of the sprite in the world space. Attach box collider2D to get the exact size")]
    public float verticalSize;
    
    private void Update()
    {
        if (transform.position.y < -verticalSize) //Se lo sprite va sotto la viewport muovo l'oggetto sopra la viewport
        {
            RepositionBackground();
        }
    }

    void RepositionBackground() 
    {
        Vector2 groundOffSet = new Vector2(0, verticalSize * 2f);
        transform.position = (Vector2)transform.position + groundOffSet;
    }
}
