import React from 'react';
// eslint-disable-next-line import/no-extraneous-dependencies
import { Outlet, useLocation } from 'react-router-dom';
// eslint-disable-next-line import/no-unresolved
import Header from '@components/features/Header';
// eslint-disable-next-line import/no-unresolved
import SideBar from '@components/features/SideBar';
// eslint-disable-next-line import/no-unresolved
import styled from 'styled-components';
import PATHS from '../../routes/path';

const Layout = () => {
  const location = useLocation();

  const isIntroducePage = location.pathname === PATHS.ROOT;

  return (
    <Container>
      <Header />
      <Content>
        {!isIntroducePage && <SideBar />}
        <Main>
          <Outlet />
        </Main>
      </Content>
    </Container>
  );
};

export default Layout;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const Content = styled.div`
  display: flex;
  flex: 1;
`;

const Main = styled.main`
  flex: 1;
  overflow-y: auto;
  background-color: #f9fafb;
`;
