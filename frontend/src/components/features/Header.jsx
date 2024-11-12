import React from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import { Common } from '@styles/globalStyle';

export const HEADER_HEIGHT = '9vh';

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
  box-shadow: 0px 0.4vh 0.6vh rgba(0, 0, 0, 0.1); // 그림자 높이도 vh 단위로 변경
  z-index: 9999;
  position: relative;
`;

const LogoContainer = styled.div`
  display: flex;
  align-items: center;
  margin-left: 20vw; // 화면 너비에 맞춰 여백 설정
  margin-right: 20vw;
`;

const LogoImage = styled.img`
  width: 3vh; // vh 단위로 로고 크기 설정
  height: 3vh;
  margin-right: 1vh;
`;

const LogoText = styled.h1`
  font-size: 2vh; // vh 단위로 폰트 크기 설정
  font-weight: bold;
  color: #000;
`;
