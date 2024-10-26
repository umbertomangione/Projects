using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Borders
{
    public float minXOffset = 3f, maxXOffset = 3f, minYOffset = 1.5f, maxYOffset = 1.5f;
    [HideInInspector] public float minX, maxX, minY, maxY;
}

public class PlayerMoving : MonoBehaviour {

    public Borders borders;
    Camera mainCamera;

    public static PlayerMoving instance;

    private void Awake()
    {
        if (instance == null)
            instance = this;
    }

    private void Start()
    {
        if(Screen.width == 640 && Screen.height == 480)
        {
            borders.minXOffset = 3f;
            borders.maxXOffset = 3f;
        }
        mainCamera = Camera.main;
        RidimensionaBordi();
    }

    private void Update()
    {
        if (ChangeController.command == 2){
            if (Input.GetMouseButton(0))
            {
                Vector3 mousePosition = mainCamera.ScreenToWorldPoint(Input.mousePosition);
                mousePosition.x = Mathf.Clamp(mousePosition.x, borders.minX, borders.maxX);
                mousePosition.y = Mathf.Clamp(mousePosition.y, borders.minY, borders.maxY);
                mousePosition.z = transform.position.z;
                transform.position = Vector3.MoveTowards(transform.position, mousePosition, 30 * Time.deltaTime);
            }
        } else {
            float horizontalDirection = Input.GetAxis("Horizontal");
            float verticalDirection = Input.GetAxis("Vertical");

            Vector3 moveDirection = new Vector3(horizontalDirection, verticalDirection, 0.0f);

            transform.position += moveDirection / 4;

            transform.position = new Vector3(Mathf.Clamp(transform.position.x, borders.minX, borders.maxX), Mathf.Clamp(transform.position.y, borders.minY, borders.maxY), 0);
        }
    }

    void RidimensionaBordi() 
    {
        borders.minX = mainCamera.ViewportToWorldPoint(Vector2.zero).x + borders.minXOffset;
        borders.minY = mainCamera.ViewportToWorldPoint(Vector2.zero).y + borders.minYOffset;
        borders.maxX = mainCamera.ViewportToWorldPoint(Vector2.right).x - borders.maxXOffset;
        borders.maxY = mainCamera.ViewportToWorldPoint(Vector2.up).y - borders.maxYOffset;
    }
}