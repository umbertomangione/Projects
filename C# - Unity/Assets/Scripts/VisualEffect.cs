using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Questo script attaccato ai 'VisualEffect' degli oggettti.
public class VisualEffect : MonoBehaviour {

    [Tooltip("the time after object will be destroyed")]
    public float destructionTime;

    private void OnEnable()
    {
        StartCoroutine(Destruction()); //Timer per la distruzione dell'oggetto
    }

    IEnumerator Destruction() //Aspetto il tempo stimato e poi distruggi l'oggetto
    {
        yield return new WaitForSeconds(destructionTime); 
        Destroy(gameObject);
    }
}
