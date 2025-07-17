<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/', name: 'app_accueil')]
class AccueilController extends AbstractController
{
    //Accès à la page d'accueil
    #[Route('', name: '')]
    public function index(): Response
    {
        return $this->render('pageaccueil.html.twig');
    }
}





