import React from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import { Common } from '@styles/globalStyle';

export const HEADER_HEIGHT = '76px';

const Header = () => (
  <HeaderContainer>
    <LogoContainer>
      <LogoImage src="/image/logo.png" alt="Agile Mentor Logo" />
      <LogoText>Agile Mentor</LogoText>
    </LogoContainer>
  </HeaderContainer>
);

export default Header;

const HeaderContainer = styled.header`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: ${HEADER_HEIGHT};
  background-color: ${Common.colors.primary};
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  z-index: 9999;
  position: relative;
`;

const LogoContainer = styled.div`
  display: flex;
  align-items: center;
  margin-left: 300px;
  margin-right: 300px;
`;

const LogoImage = styled.img`
  width: 32px;
  height: 32px;
  margin-right: 10px;
`;

const LogoText = styled.h1`
  font-size: 20px;
  font-weight: bold;
  color: #000;
`;
