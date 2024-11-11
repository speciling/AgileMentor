import React from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import { Common } from '@styles/globalStyle';
import { SlArrowDown } from 'react-icons/sl';

export const HEADER_HEIGHT = '76px';

const Header = () => (
  <HeaderContainer>
    <LogoContainer>
      <LogoImage src="/image/logo.png" alt="Agile Mentor Logo" />
      <LogoText>Agile Mentor</LogoText>
    </LogoContainer>
    <NavMenu>
      <NavItem href="#about">
        About <SlArrowDownIcon />
      </NavItem>
      <NavItem href="#guide">
        Guide <SlArrowDownIcon />
      </NavItem>
      <NavItem href="#blog">
        Blog <SlArrowDownIcon />
      </NavItem>
    </NavMenu>
  </HeaderContainer>
);

export default Header;

const HeaderContainer = styled.header`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: ${HEADER_HEIGHT};
  background-color: ${Common.colors.primary};
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

const NavMenu = styled.nav`
  display: flex;
  gap: 70px;
`;

const NavItem = styled.a`
  font-size: 16px;
  color: #333;
  text-decoration: none;
  position: relative;
  cursor: pointer;

  &:hover::after {
    font-size: 10px;
    position: absolute;
    right: -12px;
    top: 3px;
    color: blue;
  }
`;
const SlArrowDownIcon = styled(SlArrowDown)`
  font-size: 12px;
  color: #0eaaf9;
  font-weight: bold;
  stroke-width: 80;
`;
