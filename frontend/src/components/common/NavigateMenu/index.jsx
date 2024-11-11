import React from 'react';
import styled from 'styled-components';
import { FaFolder, FaThList, FaBook, FaChartLine } from 'react-icons/fa';

const NavigateMenu = () => (
  <Container>
    <Title>이용하기</Title>
    <MenuItem>
      <FaFolder />
      <MenuText>내 프로젝트</MenuText>
    </MenuItem>
    <MenuItem>
      <FaThList />
      <MenuText>칸반보드</MenuText>
    </MenuItem>
    <MenuItem>
      <FaBook />
      <MenuText>애자일 학습하기</MenuText>
    </MenuItem>
    <MenuItem>
      <FaChartLine />
      <MenuText>번다운 차트</MenuText>
    </MenuItem>
  </Container>
);

export default NavigateMenu;

const Container = styled.div`
  width: 100%;
  padding: 0 20px 20px 20px;
`;

const Title = styled.h3`
  display: flex;
  align-items: center;
  align-self: flex-start;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
  color: #151313;
`;

const MenuItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  color: #666;
  font-size: 18px;
  cursor: pointer;
  transition: color 0.3s;

  &:hover {
    color: #333;
  }
`;

const MenuText = styled.span`
  margin-left: 8px;
`;
