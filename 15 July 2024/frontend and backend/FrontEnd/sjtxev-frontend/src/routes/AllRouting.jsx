import React, { lazy, Suspense } from "react";
import { BrowserRouter, Routes, Route, Outlet, Navigate, useRoutes } from "react-router-dom";

import DashboardLayout from '../layouts/dashboard';

export const Home = lazy(() => import('../pages/home'));
export const LogIn = lazy(() => import('../pages/log-in'));
export const SignUp = lazy(() => import('../pages/sign-up'));
export const Inquiry = lazy(() => import('../pages/inquiry'));
export const Vehicle = lazy(() => import('../pages/vehicle'));
export const Page404 = lazy(() => import('../pages/page-not-found'));

export const AllRouting = () => {
  const routes = useRoutes([
    {
      element: (
        <DashboardLayout>
          <Suspense>
            <Outlet />
          </Suspense>
        </DashboardLayout>
      ),
      children: [
        { element: <Home />, index: true },
        { path: 'inquiries', element: <Inquiry /> },
        { path: 'vehicles', element: <Vehicle /> },
      ],
    },
    {
      path: 'log-in',
      element: <LogIn />,
    },
    {
      path: 'sign-up',
      element: <SignUp />,
    },
    {
      path: '404',
      element: <Page404 />,
    },
    {
      path: '*',
      element: <Navigate to="/404" replace />,
    },
  ]);

  return routes;
};
